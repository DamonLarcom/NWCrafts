<script>
	import { store } from './../store';
	import RequestCard from './../Components/RequestCard.svelte';
    import router from "page"
    import { onMount } from 'svelte';

    let reqTo = []
    let reqFrom = []

    function onUpdate() {
        router.redirect("/profile/{$store}/update")
    }
    function onTrade() {
        router.redirect("/trade")
    }

    onMount(async() => {
        await fetch("http://localhost:8080/user/"+ $store + "/reqTo", {
            mode: "cors",
            headers: {
                "Authorization" : "Bearer " + document.cookie.substring("access_token=".length)
            }
        }).then(res => res.json())
        .then(data => {
            reqTo = data
            console.log(reqTo)
        });
        await fetch("http://localhost:8080/user/"+ $store + "/reqFrom", {
            mode: "cors",
            headers: {
                "Authorization" : "Bearer " + document.cookie.substring("access_token=".length)
            }
        }).then(res => res.json())
        .then(data => reqFrom = data);
    })
</script>

<div id="wrapper">
    <div class="left">
        <h2>{$store}'s Profile</h2>
        <button on:click={onUpdate}>Update Profile</button>
        <button on:click={onTrade}>Add a new Trade</button>
    </div>
    <div class="requests">
        <div class="reqTo">
            <h1>Requests To</h1>
            {#each reqTo as request}
                <RequestCard  {request}/>
            {/each}
        </div>
        <div class="reqFrom">
            <h1>Requests From</h1>
            {#each reqFrom as request}
                 <RequestCard {request}/>
            {/each}
        </div>
    </div>
</div>

<style>
    #wrapper {
        justify-content: center;
        padding: 35px;
        height: 90%;
        margin: 50px auto;
        display: flex;
        flex-direction: row;
        text-align: center;
    }
    h2, h1{
        text-align: center;
        color: #2424FF;
    }
    .left, .requests {
        background-color: #1e1e1e;
        border-radius: 15px;
        margin: 15px;
    }
    .left {
        flex: 1;
        height: 25%;
    }
    .requests {
        flex: 2;
        height: 100%;

        display: flex;
        flex-direction: row;
    }

    .reqTo, .reqFrom {
        background-color: #1e1e1e;
        border-radius: 15px;
        width: 100%;
        height: 100%;
        flex: 1;
        margin: 0 5px;
    }
</style>