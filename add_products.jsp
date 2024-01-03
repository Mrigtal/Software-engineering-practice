<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商家添加商品</title>
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
        form {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #333;
            color: #fff;
            border: none;
            cursor: pointer;
            text-decoration: none;
            margin-right: 10px;
        }
        .button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<div class="container">
        <h1>商家添加商品</h1>
        <form action="AddProductServlet" method="POST">
            <label for="productName">商品名称:</label>
            <input type="text" id="productName" name="productName" required>
            
            <label for="productInfo">商品信息:</label>
            <textarea id="productInfo" name="productInfo" rows="4" required></textarea>
            
            <label for="productPrice">商品价格:</label>
            <input type="text" id="productPrice" name="productPrice" required>
            
            <button type="submit" class="button">提交</button>%%提交的界面为AddProductServlet，你可以改一下--%
        </form>
        
        <a href="" class="button">返回商家管理页面</a>
        
        <a href="shopping.html" class="button">返回购物页面并退出账号</a>
    </div>
</body>
</html>