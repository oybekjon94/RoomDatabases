package com.example.roomdatabacepractice.async

//biz CallBackni yozishdan maqsad Listener yaratishdir.
//Hoy boshliq hozir men progressni boshliyman sen progressni korsat
//bunda ikkita func. boladi
//1) progressni korsatish va ochirish
//2) finishni bildirish
interface AddTodoCallBack {
     fun onControlProgressBar()
     fun onFinish()
}