<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <% request.setCharacterEncoding("UTF-8"); %>

        <h2> タイトル <c:out value="${title.title}" /></h2>


        <p>内容：<c:out value="${title.content}" /></p>
        <p>作成日時：<fmt:formatDate value="${title.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></p>

        <c:forEach var="reply" items="${replys}" varStatus="status">
                <br>   返信  <c:out value="${reply.reply}" />
                <br>
         </c:forEach>

         <div id="pagination">
            （全 ${replys_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((replys_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                         <a href="<c:url value='/replys/index?page=${i}&id=${title.id}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/replys/new?id=${title.id}' />">コメントの返信</a></p>



        <p><a href="${pageContext.request.contextPath}/titles/index?id=${title.id}">一覧に戻る</a></p>

    </c:param>
</c:import>