<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cadastro de Usuários</title>
</head>
<body>
    <center>
        <h1>Cadastro de Usuário</h1>
        <h2>
            <a href="/trabalho_graduacao/new" class="botao">Novo Usuário</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5" id="tabela">
            <caption><h2>Lista de Usuários</h2></caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th>Idade</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="usuario" items="${listUsuario}">
                <tr>
                    <td><c:out value="${usuario.id}" /></td>
                    <td><c:out value="${usuario.nome}" /></td>
                    <td><c:out value="${usuario.email}" /></td>
                    <td><c:out value="${usuario.idade}" /></td>
                    <td>
                        <a href="/trabalho_graduacao/edit?id=<c:out value='${usuario.id}' />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/trabalho_graduacao/delete?id=<c:out value='${usuario.id}' />">Deletar</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>

<style>
	body {
		font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	}
	
	.botao {
		color: white;
		font-size: 20px;
		text-decoration: none;
		border: 1px #004f8d solid;
		border-radius: 5px;
		padding: 10px;
		background-color: #f54c69;
	}

	#tabela {
		border-collapse: collapse;
		width: 80%;
	}
	
	#tabela td, #tabela th {
		border: 1px solid #ddd;
		padding: 8px;
	}
	
	#tabela tr:nth-child(even) {
		background-color: #f2f2f2;
	}
	
	#tabela tr:hover {
		background-color: #ddd;
	}
	
	#tabela th {
		padding-top: 12px;
		padding-bottom: 12px;
		text-align: left;
		background-color: pink;
		color: white;
	}
</style>

</html>