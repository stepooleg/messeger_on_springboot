<#macro logout>

<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary" value="Sign In"/> Sign Out</button>
</form>
</#macro>