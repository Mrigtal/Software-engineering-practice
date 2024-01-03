<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商家管理首页</title>
 <style>
        body {
            font-family: Arial, sans-serif;           
            margin: 20px;         
            background-color: #f2f2f2;
                   }
        .container {
            border: 1px solid #ccc;            
            padding: 20px;           
            background-color: #fff;        
            width: 400px;          
            margin: 0 auto;       
            }       
             h1 {
            color: #333;
        }
        p {
            margin-bottom: 10px;        
            }
        ul {           
        list-style-type: none;           
        padding: 0;        
        }        
        li {            
        margin-bottom: 5px;        
        }
        .button {           
        display: inline-block;
            padding: 10px 20px;           
             background-color: #333;
            color: #fff;           
            border: none;
            cursor: pointer;
            text-decoration: none;
            margin-right: 10px;      }        
            .button:hover {            
            background-color: #555;
        }    
        </style>   
     <script>
        function checkLogin() {
            // 检查用户是否已登录
            var loggedIn = sessionStorage.getItem("loggedIn");
            if (!loggedIn) {
                // 若未登录，跳转到登录页面
                window.location.href = "login.html";
            }
        }
    </script>
</head>
<body>
 </script>
 </head>
 <body >
    <div class="container">
        <h1>商家管理首页</h1>        
        <p>欢迎回来，商家！</p >        
        <p>请选择您要进行的操作：</p >        
        <ul>
            <li><a href=" " class="button">添加商品</a></li>            
            <li><a href="manage-products.html" class="button">管理商品</a></li>
        </ul>
        <button onclick="logout()" class="button">返回购物页面并退出账号</button>  
        </div>
            <script>
        function logout() {
            // 清除登录状态并返回购物页面
            sessionStorage.removeItem("loggedIn");
            window.location.href = "shopping.html";
        }
    </script>
   
</body>
</html>