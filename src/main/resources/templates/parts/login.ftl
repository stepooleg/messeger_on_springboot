<#macro login path isRegisterForm>

<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name : </label>
        <div class="col-sm-6">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid','')}"
                   placeholder="Enter User name"/>
            <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
        </#if>
    </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password: </label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                   placeholder="Enter password"/>
            <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
        </#if>
    </div>
    </div>
    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password: </label>
        <div class="col-sm-6">
            <input type="password" name="passwordValid"
                   class="form-control ${(passwordValidError??)?string('is-invalid','')}"
                   placeholder="Enter retype password"/>
            <#if passwordValidError??>
            <div class="invalid-feedback">
                ${passwordValidError}
            </div>
        </#if>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email: </label>
        <div class="col-sm-6">
            <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                   class="form-control ${(emailError??)?string('is-invalid','')}"
                   placeholder="Enter your email"/>
            <#if emailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
        </#if>
    </div>
    </div>
</#if>
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<#if !isRegisterForm><a href="/registration">Add new user</a></#if>
<button type="submit" class="btn btn-primary"/><#if isRegisterForm>Create<#else>Sign In</#if></button>
</form>
</#macro>
