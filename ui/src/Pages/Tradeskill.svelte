<script>
	import { store } from './../store';
    import router from "page"

    let type;
    function confirmTrade() {
        fetch("http://localhost:8080/trade", {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
                "Authorization" : "Bearer " + document.cookie.substring("access_token=".length)
            },
            body: JSON.stringify({type})
        });
        router.redirect("/profile/" + $store)
    }
</script>

<div id="wrapper">
    <form id="trade_form">
        <h2>Add trade</h2>
        <select name="type" id="type" required=true bind:value={type}>
            <option value="WEAPONSMITHING">Weaponsmithing</option>
            <option value="ARMORING">Armoring</option>
            <option value="ARCANA">Arcana</option>
            <option value="ENGINEERING">Engineering</option>
            <option value="JEWELCRAFTING">Jewelcrafting</option>
            <option value="FURNISHING">Furnishing</option>
        </select>
        <button type="submit" on:click|preventDefault={confirmTrade}>Add Trade</button>
        <p>Misclick? <a href="/profile/{$store}">Go back to profile</a></p>
    </form>
</div>

<style>
    #wrapper {
        justify-content: center;
    }

    #trade_form {
        background-color: #1e1e1e;
        border-radius: 15px;
        padding: 35px;
        margin: 50px auto;
        width: 15%;
        display: flex;
        flex-direction: column;
    }
    h2{
        text-align: center;
    }
    h2, p {
        color: #2424FF;
    }
    a {
        text-decoration: underline;
    }
</style>