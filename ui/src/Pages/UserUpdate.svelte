<script>
	import router from 'page';
	import { store } from './../store';
    let username = $store
    let password;
    let server;
    let email;
    let alignment;
    let crafter;

    function checkValid() {
        return document.getElementById("user").value.length > 1
    }
    function onClick() {
        console.log({username,password,email,server,alignment,crafter})
        fetch("http://localhost:8080/user/" + $store, {
            method: "PUT",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
                "Authorization" : "Bearer " + document.cookie.substring("access_token=".length)
            },
            body: JSON.stringify({username,password,email,server,alignment,crafter})
        });
        router.redirect("/")
    }
</script>

<div id="wrapper">
    <form id="register_form">
        <h2>Register</h2>
        <input id="user" name="username" value={username} disabled/>
        <input id="password" name="password" type="password" placeholder="Password" bind:value={password}/>
        <input id="email" name="email" type="email" placeholder="Email" bind:value={email}/>
        <select name="server" id="server" required=true bind:value={server}>
            <option disabled hidden selected>Choose your server</option>
            <optgroup label="US West">
                <option value="Camelot">Camelot</option>
                <option value="Diranda">Diranda</option>
                <option value="El Dorado">El Dorado</option>
                <option value="Lilliput">Lilliput</option>
                <option value="Midian">Midian</option>
                <option value="Neno Kuni">Neno Kuni</option>
                <option value="Rivadeneyra">Rivadeneyra</option>
                <option value="Yggdrasil">Yggdrasil</option>
            </optgroup>
            <optgroup label="US East">
                <option value="Calnogor">Calnogor</option>
                <option value="Castle Of Steel">Castle Of Steel</option>
                <option value="City Of Brass">City Of Brass</option>
                <option value="Eden">Eden</option>
                <option value="Frislandia">Frislandia</option>
                <option value="Krocylea">Krocylea</option>
                <option value="Maramma">Maramma</option>
                <option value="Oceana">Oceana</option>
                <option value="Ohonoo">Ohonoo</option>
                <option value="Olympus">Olympus</option>
                <option value="Orofena">Orofena</option>
                <option value="Orun">Orun</option>
                <option value="Pleroma">Pleroma</option>
                <option value="Pluto">Pluto</option>
                <option value="Pyrallis">Pyrallis</option>
                <option value="Silha">Silha</option>
                <option value="Valhalla">Valhalla</option>
                <option value="Yaxche">Yaxche</option>
                <option value="Ys">Ys</option>
                <option value="Zuvendis">Zuvendis</option>
            </optgroup>
        </select>
        <select name="alignment" id="alignment" required=true bind:value={alignment}>
            <option disabled hidden selected>Choose your faction</option>
            <option value="COVENANT">Covenant</option>
            <option value="MARAUDERS">Marauders</option>
            <option value="SYNDICATE">Syndicate</option>
        </select>
        <div id="check">
            <input type="checkbox" id="crafter" name="crafter" bind:checked={crafter}><label for="crafter" class:disabled={!crafter}>Crafter</label>
        </div>
        <button type="submit" disabled={!checkValid} on:click|preventDefault={onClick}>Register</button>
        <p>Misclick? <a href="/profile/{$store}">Go back to profile</a></p>
    </form>
</div>

<style>
    #wrapper {
        justify-content: center;
    }

    #register_form {
        background-color: #1e1e1e;
        border-radius: 15px;
        padding: 35px;
        margin: 50px auto;
        width: 15%;
        display: flex;
        flex-direction: column;
        text-align: center;
    }
    h2{
        color: #2424FF;
        text-align: center;
    }
    label {
        display: inline-block;
        color: white;
        background: green;
        padding: 2em;
        border-radius: 1em;
        user-select: none;
        border: 3px solid aqua;
        margin: 0.5em 0;
    }
    label:hover {
        cursor: pointer;
    }

    label.disabled {
        opacity: 0.6;
        background: red;
        color: black;
        border: 3px solid black;
    }

    #check {
        text-align: center;
    }

    input[type="checkbox"] {
        display: none;
    }

    h2, p {
        color: #2424FF;
    }
    a {
        text-decoration: underline;
    }
</style>