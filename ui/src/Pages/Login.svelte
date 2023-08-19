<script>
    import { prevent_default } from "svelte/internal";
    import {store} from "../store"
    import router from "page"
    let username = "";
    let password = "";
    function confirmLogin() {
        fetch("http://localhost:8080/authenticate", {
            method: "POST",
            headers: {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify({username, password})
        }).then(res => res.json())
        .then(data => {
            document.cookie = "access_token="+ data.access_token
            let token = document.cookie.substring("access_token=".length)
            let payload = token.split('.')[1]
            let decoded_token = atob(payload)
            $store = JSON.parse(decoded_token).sub
            router.redirect("/")
        })
    }
</script>

<div id="wrapper">
    <form id="login_form">
        <h2>Login</h2>
        <input id="user" name="username" placeholder="Username" bind:value={username}/>
        <input id="password" name="password" type="password" placeholder="Password" bind:value={password}/>
        <button type="submit" on:click|preventDefault={confirmLogin}>Login</button>
        <p>Don't have an account? <a href="/register">Sign up here</a></p>
    </form>
</div>

<style>
    #wrapper {
        justify-content: center;
    }

    #login_form {
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