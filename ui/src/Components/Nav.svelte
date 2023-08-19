<script>
import { onMount } from "svelte";

    import {store} from "../store"

    function handleLogout() {
        document.cookie = "access_token=;expires=Sun, 20 Aug 2000 12:00:00 UTC"
        $store = null
    }
</script>
<div id="nav-main">
    <a href="/"><img src="/new-world.png" alt ="new world logo"/></a>
    <h2>NWCrafts</h2>
    <ul>
        {#if $store != null}
            <li><p>Welcome, <a href="/profile/{$store}">{$store}</a></p></li>
            <li><a href="/logout" on:click={handleLogout}>Log Out</a></li>
        {/if}
    </ul>
</div>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Mina:wght@700&display=swap');

    #nav-main {
        display: flex;
        border-radius: 0 0 15px 15px;
        background-color: #1e1e1e;
        flex-direction: row;
        justify-content: left;
    }
    img {
        max-width: 55px;
        max-height: 55px;
        margin: 5px;
    }
    ul {
        display: flex;
        flex-direction: row;
        justify-content: right;
        flex:1;
    }
    li {
        display: inline-block;
        list-style: none;
        font-size: 20px;
        font-family: 'Mina';
        padding: 0 25px;
    }
    a {
        color: #2424FF;
        text-decoration: underline;
    }
    a:hover {
        text-decoration: none;
        color: goldenrod;
    }
    h2 {
        color: whitesmoke;
        font-family: Mina;
    }
    p {
        margin-top: 0;
        color: #2424FF;
    }
</style>