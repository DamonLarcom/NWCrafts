<script>
	import RequestCard from './RequestCard.svelte';
    export let factions = [];
    export let trade;
</script>

<div id="wrapper">
    <h1>Filters</h1>
    <h2>Click to exclude factions</h2>
    <label>
        <input type=checkbox bind:group={factions} name="factions" value="MARAUDERS">
        Marauders
    </label>
    <label>
        <input type=checkbox bind:group={factions} name="factions" value="COVENANT">
        Covenant
    </label>
    <label>
        <input type=checkbox bind:group={factions} name="factions" value="SYNDICATE">
        Syndicate
    </label>
    <h2>Trade Skills</h2>
    <label>
        <input type=radio bind:group={trade} name="trades" value=""/>
        No trade
    </label>
    <label>
        <input type=radio bind:group={trade} name="trades" value="WEAPONSMITHING"/>
        Weaponsmithing
    </label>
    <label>
        <input type=radio bind:group={trade} name="trades" value="ARMORING"/>
        Armoring
    </label>
    <label>
        <input type=radio bind:group={trade} name="trades" value="ARCANA"/>
        Arcana
    </label>
    <label>
        <input type=radio bind:group={trade} name="trades" value="ENGINEERING"/>
        Engineering
    </label>
    <label>
        <input type=radio bind:group={trade} name="trades" value="JEWELCRAFTING"/>
        Jewelcrafting
    </label>
    <label>
        <input type=radio bind:group={trade} name="trades" value="FURNISHING"/>
        Furnishing
    </label>
</div>

<style>
    #wrapper {
        background-color: #1e1e1e;
        border-radius: 15px;
        height: 50%;
        display: flex;
        padding: 30px;
        flex-direction: column;
        justify-content: left;
        align-items: flex-start;
    }
    h1 {
        color: #2424FF;
        justify-self: center;
    }
    input{
        height: 30px;
        width: 30px;
    }
    label {
        font-size: 20pt;
        color:whitesmoke;
    }
    h2 {
        color: #2424FF;
    }
</style>