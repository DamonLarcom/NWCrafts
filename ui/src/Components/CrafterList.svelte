<script>
    import { onMount } from 'svelte';
	import Crafter from './Crafter.svelte';
    import { store } from './../store';
    import {Stretch} from "svelte-loading-spinners"

    let craftersList = []
    let isLoaded = false;
    export let search;
    export let factions;
    export let trade = "";
    $: filtered = craftersList.filter(crafter =>  
        factions.indexOf(crafter.alignment) == -1
        && (crafter.tradeSkills.includes(trade) || trade==="")
        && crafter.username !== $store
        && crafter.tradeSkills.length > 0
        && (crafter.username.toLowerCase().startsWith(search.toLowerCase()) 
        || crafter.server.toLowerCase().startsWith(search.toLowerCase())))
    

    onMount(async () => {
        await fetch("http://localhost:8080/user/crafters", {
            headers: {
                "Authorization" : "Bearer " + document.cookie.substring("access_token=".length)
            }
        })
        .then(res => res.json())
        .then(data => {
            craftersList = data;
            console.log(craftersList)
            for(let i = 0; i < craftersList.length; i++) {
                let skills = craftersList[i].tradeSkills.map(skill => skill.type);
                craftersList[i].tradeSkills = skills;
            }
            isLoaded = true;
        });
    })
</script>

<div id="wrapper">
    {#if !isLoaded}
        <Stretch class="stretch" size="60" color="#2424FF" unit="px"/>
    {/if}
    {#if filtered.length < 1 && isLoaded}
        <h2>No crafters exist with provided filters.</h2>
    {:else}
         {#each filtered as crafter}
              <Crafter {crafter}/>
         {/each}
    {/if}
</div>

<style>
    #wrapper {
        display: flex;
        flex-direction: row;
        justify-content:flex-start;
        flex-wrap: wrap;
        background-color: #1e1e1e;
        border-radius: 15px;
        height: 100%;
        /* margin-top: 20px; */
        padding: 15px;

        overflow: scroll;
        overflow-x: hidden;
    }
    /* width */
    ::-webkit-scrollbar {
        width: 15px;
    }

    /* Track */
    ::-webkit-scrollbar-track {
        background: #595959;
        border-radius: 15px; 
    }
    
    /* Handle */
    ::-webkit-scrollbar-thumb {
        background: #4a4a4a; 
        border-radius: 25px;
    }
    h2 {
        color: whitesmoke;
    }
</style>