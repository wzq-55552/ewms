
const routes = [
  {
    path: '/Main',
    component: () => import('layouts/Main.vue'),
    children:[ {
      path:'setUserInfo',
      component:()=>import('components/setUserInfo.vue')
    }, {
      path:'Permission',
      component:()=>import('components/Permission.vue')
    }, {
      path:'editUserInfo',
      component:()=>import('components/editUserInfo.vue')
    },{
      path:'queryLoginState',
      component:()=>import('components/queryLoginState.vue')
    },{
      path:'setSystemParm',
      component:()=>import('components/setSystemParm.vue')
    },{
      path:'main',
      component:()=>import('components/main.vue')
    }]
  },
  {
    path:'/login',
    component:() =>import('layouts/eWMS.vue')
  },{
    path:'/',
    redirect: '/login'
  }
]

// Always leave this as last one
// if (process.env.MODE !== 'ssr') {
//   routes.push({
//     path: '*',
//     component: () => import('pages/Error404.vue')
//   })
// }

export default routes
