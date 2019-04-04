import api from '@/utils/api'

export function login(username,password){
  return api({
    url:'/user/login',
    method: 'post',
    data:{
      username,
      password
    }
  })
}

export function getInfo(token) {
  return api({
    url: '/login/getInfo',
    method: 'post',
    params: {token}
  })
}

export function logout(){
  return api({
    url:'/user/logout',
    method:'post',
  })
}




