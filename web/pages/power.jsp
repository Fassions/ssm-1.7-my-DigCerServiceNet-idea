<script type="text/javascript">
    <c:if test="${sessionScope.gaUser.userType!=1}">
        top.location = "/logOut.do";
    </c:if>
</script>