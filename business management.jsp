<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商家管理商品</title>
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
            width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f2f2f2;
        }
        .status {
            display: inline-block;
            padding: 5px 10px;
            border-radius: 4px;
            font-size: 14px;
            font-weight: bold;
        }
        .active {
            background-color: #4CAF50;
            color: #fff;
        }
        .inactive {
            background-color: #f44336;
            color: #fff;
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
<h1>商家管理商品</h1>
        <table>
            <tr>
                <th>商品名称</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>商品1</td>
                <td>
                    <span class="status active">上架</span>
                </td>
                <td>
                    <button class="button">下架</button>
                </td>
            </tr>
            <tr>
                <td>商品2</td>
                <td>
                    <span class="status inactive">下架</span>
                </td>
                <td>
                    <button class="button">上架</button>
                </td>
            </tr>
            <!-- 添加更多商品行 -->
        </table>
        <a href="" class="button">返回商家管理页面</a>
        
        <a href="shopping.html" class="button">返回购物页面并退出账号</a>
    </div>
</body>
</html>