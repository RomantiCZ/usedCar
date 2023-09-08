<template>
  <div class="page-not-found">
    <h1>Oops!</h1>
    <p>404</p>
    <p>页面未找到</p>
    <router-link to="/" class="go-home">
      返回首页
      <span class="button-fill"></span>
    </router-link>
  </div>
</template>

<script>
export default {
name: "NotFoundPage",

}
</script>

<style scoped>
.page-not-found {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  font-family: Arial, sans-serif;
  text-align: center;
}

.page-not-found h1 {
  font-size: 10rem;
  color: #d93333;
  margin-bottom: -1rem;
  animation-name: wobble;
  animation-duration: 1s;
  animation-iteration-count: infinite;
}

.page-not-found p {
  font-size: 2rem;
  color: #424242;
  text-transform: uppercase;
  letter-spacing: 0.2em;
  margin:  1rem 0 0 0;
}

.go-home {
  position: relative;
  display: inline-block;
  font-size: 1.5rem;
  background-color: transparent;
  color: #03a9f4;
  border-radius: 50px;
  padding: 1rem 3rem;
  margin-top: 2rem;
  text-decoration: none;
  transition: all 0.3s ease;
  overflow: hidden;
  color: #c13838;

}

.go-home .button-fill {
  position: absolute;
  top: 0;
  left: 0;
  width: 0%;
  height: 100%;
  background-color: #94dfd8;
  transition: all 0.5s ease;
}

.go-home:hover .button-fill {
  width: 100%;
}

@keyframes wobble {
  0% {
    transform: rotate(-5deg);
  }
  50% {
    transform: rotate(5deg);
  }
  100% {
    transform: rotate(-5deg);
  }
}
</style>