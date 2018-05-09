<#import "parts/common.ftl" as i>
<@i.page>
<body>
    User Edit
<form action="/user" method="post">
    <div><label> User name: <input type="text" name="username" value="${user.username}"/> </label></div>
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
    </div>
</#list>
    <input type="hidden" name="userId" value="${user.id}"/> </label>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</@i.page>