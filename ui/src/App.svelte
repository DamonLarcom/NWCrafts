<script>
	import CraftRequest from './Pages/CraftRequest.svelte';
	import Tradeskill from './Pages/Tradeskill.svelte';
	import Sidebar from './Components/Sidebar.svelte';
	import { store } from './store';
	import router from "page";
	import Nav from "./Components/Nav.svelte";
	import Home from "./Pages/Home.svelte";
	import Login from "./Pages/Login.svelte";
	import Register from './Pages/Register.svelte';
	import Profile from './Pages/Profile.svelte';
	import UserUpdate from './Pages/UserUpdate.svelte';

	let page;
	let params;

	// routing for pages
	router('/', () => page = Home);
	router('/login', () => page = Login);
	router('/register', () => page = Register)
	router('/profile/:id', (ctx, next) => {
		params = ctx.params;
		next();
	}, () => page= Profile)
	router('/profile/:id/update', (ctx, next) => {
		params = ctx.params
		next();
	},() => page = UserUpdate)
	router('/trade', () => page = Tradeskill)
	router('/request',(ctx, next) => {
		params = ctx.params;
		next();
	},() => page = CraftRequest)

	router.start();
	router.redirect("/login")
</script>

<main>
	<!-- Put view code here -->
	<Nav/>
	<svelte:component this={page} {params}/>
</main>

<style>
	main {
		height: 90%;
	}
</style>