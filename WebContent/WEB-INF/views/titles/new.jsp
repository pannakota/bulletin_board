<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>記事　作成ページ</h2>

        <form method="POST" action="<c:url value='/titles/create' />">
            <c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="title">タイトル</label><br />
<input type="text" name="title" value="${title.title}" />
<br /><br />

<label for="title">内容</label><br />
<textarea name="content" cols="170" rows="14" ><c:out value="${title.content}"></c:out></textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>
        </form>

        <p><a href="<c:url value='/titles/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>