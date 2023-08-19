<script>
	import Sidebar from './../Components/Sidebar.svelte';
	import CrafterList from './../Components/CrafterList.svelte';
	import router from 'page';
    import { onMount } from 'svelte';
	import { store } from './../store';

    let search = "";
    let factions;
    let trade;

    onMount(() => {
        if(!$store) {
            router.redirect("/login")
        }
    })
</script>

<div id="wrapper">
    <div id="main">
        <CrafterList {search} {factions} {trade}/>
    </div>
    <div id="sidebar">
        <div id="wrapper2">
            <input bind:value={search} placeholder="Search for User or Server..."/>
            <Sidebar bind:factions={factions} bind:trade={trade}/>
        </div>
    </div>
</div>

<style>
    #wrapper {
        display: flex;
        flex-direction: row;
        height: 100%;
        padding: 15px;
    }
    #sidebar {
        flex: 1;
        margin: 0 15px;
    }
    #main {
        flex: 3;
        margin: 0 15px;
    }
    #wrapper2 {
        background-color: #1e1e1e;
        border-radius: 15px;
        height: 100%;

        text-align: center;
    }
    input {
        width: 100%;
        height: 50px;
        font-size: 15pt;
        border-radius: 15px 15px 0 0;
    }
</style>