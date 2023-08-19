<script>
	import { store } from './../store';
    import router from "page"
    export let request;

    function handleToggle() {
        fetch("http://localhost:8080/craft/" + request.requestId + "/toggle", {
            method: "PATCH",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
                "Authorization" : "Bearer " + document.cookie.substring("access_token=".length)
            }
        });
        router.redirect("/")
    }
</script>

<div id="wrapper">
    <div class="trade_icon">
        {#if request.trade === "WEAPONSMITHING"}
            <img src="/assets/weaponsmithing.png" class="trade_icon" alt="trade"/>
        {:else if request.trade === "ARMORING"}
            <img src="/assets/armoring.png" class="trade_icon" alt="trade"/>    
        {:else if request.trade === "ARCANA"}
            <img src="/assets/arcana.png" class="trade_icon" alt="trade"/>      
        {:else if request.trade === "JEWELCRAFTING"}
            <img src="/assets/jewelcrafting.png" class="trade_icon" alt="trade"/>    
        {:else if request.trade === "FURNISHING"}
            <img src="/assets/furnishing.png" class="trade_icon" alt="trade"/>   
        {:else if request.trade === "ENGINEERING"}
            <img src="/assets/engineering.png" class="trade_icon" alt="trade"/>  
        {/if}

        {#if request.requestee.username == $store && request.status != "FULFILLED"}
             <button on:click={handleToggle}>Mark as Fulfilled</button>
        {/if}
    </div>
    <div class="text_div">
        {#if request.requester.username == $store}
            <h3>Requester: You</h3>
            <h3>Requestee: {request.requestee.username}</h3>
        {:else}
            <h3>Requester: {request.requester.username}</h3>
            <h3>Requestee: You</h3>
        {/if}
        <h3>Tip: {request.tip}</h3>
        {#if request.status == "FULFILLED"}
            <h3>Status: <strong class="status_fulfilled">{request.status}</strong></h3>
        {:else}
            <h3>Status: <strong class="status">{request.status}</strong></h3>
        {/if}
    </div>
</div>

<style>
    #wrapper {
        background-color: #383838;
        border-radius: 15px;
        width: 90%;
        margin: 10px;

        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }
    h3 {
        color: whitesmoke;
    }
    .trade_icon {
        flex: 1;
        align-items: center;
        justify-content: center;
    }
    .text_div {
        flex: 2;
    }
    img {
        max-width: 150px;
        max-height: 150px;
    }
    .status {
        color: orangered;
    }
    .status_fulfilled {
        color:green;
    }
</style>