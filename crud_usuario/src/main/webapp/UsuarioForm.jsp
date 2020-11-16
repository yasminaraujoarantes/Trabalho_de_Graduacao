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
            <a id="btnListarUsuarios" href="/trabalho_graduacao/list" class="botao">Lista de Usuários</a>             
        </h2>
    </center>
    <div align="center">
        <c:if test="${usuario != null}">
            <form action="update" method="post" onsubmit="editadoComSucesso();">
        </c:if>
        <c:if test="${usuario == null}">
            <form action="insert" method="post" onsubmit="salvoComSucesso();">
        </c:if>
        <table border="1" cellpadding="5" id="tabelaForm">
            <caption>
                <h2 id="labelFormNovoOuEditar">
                    <c:if test="${usuario != null}">
                        Editar
                    </c:if>
                    <c:if test="${usuario == null}">
                        Novo Usuário
                    </c:if>
                </h2>
            </caption>
                <c:if test="${usuario != null}">
                    <input id="inputIdHidden" type="hidden" name="id" value="<c:out value='${usuario.id}' />" />
                </c:if>           
            <tr>
                <th>Nome*: </th>
                <td>
                    <input id="inputNome" type="text" name="nome" size="45" required="required"
                            value="<c:out value='${usuario.nome}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Email*: </th>
                <td>
                    <input id="inputEmail" type="email" name="email" size="45" required="required"
                            value="<c:out value='${usuario.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Idade: </th>
                <td>
                    <input id="inputIdade" type="number" name="idade" min="1"
                            value="<c:out value='${usuario.idade}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>Senha*: </th>
                <td>
                    <input id="inputSenha" type="password" name="senha" required="required"
                            value="<c:out value='${usuario.senha}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                   	<button id="btnSubmitForm" type="submit" class="botaoSalvar">Salvar</button>
                </td>
            </tr>
        </table>
        </form>
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
		cursor: pointer;
	}
	
	.botaoSalvar {
		color: white;
		font-size: 20px;
		text-decoration: none;
		border: 1px #006c19 solid;
		border-radius: 5px;
		padding: 10px;
		background-color: pink;
		cursor: pointer;
	}

	#tabelaForm {
		border-collapse: collapse;
	}
</style>

<script type="text/javascript">
	//<![CDATA[

	function editadoComSucesso() {
		alert('Usuário editado com sucesso!');
	}
	
	function salvoComSucesso() {
		alert('Usuário inserido com sucesso!');
	}

	//]]>
</script>

</html>