<script>
    export let crafter
    import router from "page"

    function handleClick() {
        router.redirect("/request?crafter=" + crafter.username)
    }
</script>

<div id="wrapper">
    <div class="alignment">
        {#if crafter.alignment == "MARAUDERS"}
         <img src="assets/green.png"alt=""/>
        {:else if crafter.alignment == "COVENANT"}
            <img src="assets/yellow.png"alt=""/>
        {:else if crafter.alignment == "SYNDICATE"}
            <img src="assets/purple.png"alt=""/>
        {/if}
        <button disabled on:click={handleClick}>Request a craft</button>
    </div>
    <div class="user_details">
        <h1>{crafter.username}</h1>
        <h2>Server: {crafter.server}</h2>
    </div>
    <div class="trades">
        {#each crafter.tradeSkills as skill}
            {#if skill === "WEAPONSMITHING"}
                <a href="/request?crafter={crafter.username}&type=WEAPONSMITHING">
                    <img src="assets/weaponsmithing.png" class="trade_icon" alt="trade"/>
                </a>
            {:else if skill === "ARMORING"}
                <a href="/request?crafter={crafter.username}&type=ARMORING">
                    <img src="assets/armoring.png" class="trade_icon" alt="trade"/>
                </a>      
            {:else if skill === "ARCANA"}
                <a href="/request?crafter={crafter.username}&type=ARCANA">
                    <img src="assets/arcana.png" class="trade_icon" alt="trade"/>
                </a>      
            {:else if skill === "JEWELCRAFTING"}
                <a href="/request?crafter={crafter.username}&type=JEWELCRAFTING">
                    <img src="assets/jewelcrafting.png" class="trade_icon" alt="trade"/>
                </a>      
            {:else if skill === "FURNISHING"}
                <a href="/request?crafter={crafter.username}&type=FURNISHING">
                    <img src="assets/furnishing.png" class="trade_icon" alt="trade"/>
                </a>      
            {:else if skill === "ENGINEERING"}
                <a href="/request?crafter={crafter.username}&type=ENGINEERING">
                    <img src="assets/engineering.png" class="trade_icon" alt="trade"/>
                </a>      
            {/if}
        {/each}
    </div>
</div>

<style>
     @import url('https://fonts.googleapis.com/css2?family=Mina:wght@700&display=swap');

    #wrapper{
        background-color: #383838;
        border-radius: 15px;
        height: 500px;
        margin: 20px 10px;
        width: 30%;
        padding: 15px;
    }
    img {
        max-height: 150px;
        max-width: 150px;
        flex: 1;
    }

    .alignment {
        text-align: left;
        display: flex;
        flex-direction: row;
    }

    button {
        flex: 1;
        margin: 10px 30px;
        border: none;
        border-radius: 15px;
        font-size: 20pt;
        font-family: Mina;
        color: whitesmoke;
        background-color: #1e1e1e
    }

    button:hover {
        background-color: #4a4a4a;
        color: #121212;
    }
    .user_details {
        text-align: center;
        color: whitesmoke;
    }

    h1, h2{
        font-family: Mina;
    }
    .trade_icon {
        max-height: 100px;
        max-width: 100px;
    }
    .trades {
        display: flex;
        flex: row;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
    }
</style>