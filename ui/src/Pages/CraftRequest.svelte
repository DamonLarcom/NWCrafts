<script>
	import { store } from './../store';
    import router from "page"
    import { onMount } from 'svelte';

    let trade;
    let crafter;
    let tip;

    async function getInfo() {
        const queryString = window.location.search;
        let urlparams = new URLSearchParams(queryString);
        crafter = urlparams.get("crafter")
        trade = urlparams.get("type")
    }

    function onClick() {
        fetch("http://localhost:8080/craft?crafter=" + crafter, {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
                "Authorization" : "Bearer " + document.cookie.substring("access_token=".length)
            },
            body: JSON.stringify({trade, tip})
        });
        router.redirect("/")
    }

    onMount(async() => {
        await getInfo()
    })
</script>

<div id="wrapper">
    <form id="req_form">
        <h2>Request a craft</h2>
        <label for="type">
            Trade
            <input id="trade" name="trade" bind:value={trade} disabled>
        </label>
        <label for="crafter">
            Crafter
            <input id="crafter" name="crafter" bind:value={crafter} disabled>
        </label>
        <label for="tip">
            Tip
            <input id="tip" bind:value={tip} placeholder="Enter coin amount..">
        </label>
        <button type="submit" on:click|preventDefault={onClick}>Submit Request</button>
    </form>
</div>

<style>
    #wrapper {
        justify-content: center;
    }

    #req_form {
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
        color: #2424FF;
    }
    label {
        color: whitesmoke;
        font-size: 12pt;
    }
</style>