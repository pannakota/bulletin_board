<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>記事　編集ページ</h2>

        <form method="POST" action="<c:url value='/titles/update' />">
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

        <p><a href="#" onclick="confirmDestroy();">この記事をを削除する</a></p>
                <form method="POST" action="<c:url value='/titles/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                            document.forms[1].submit();
                        }
                    }
                </script>

        <p><a href="<c:url value='/titles/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>