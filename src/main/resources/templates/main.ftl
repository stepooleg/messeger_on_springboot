<#import "parts/common.ftl" as i>

<@i.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" value="${filter?ifExists}" class="form-control" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new message
</a>
<div class="collapse <#if message?? > show </#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <input type="text" name="text" placeholder="введите сообщение"
                       value="<#if message?? > ${message.text} </#if>" class="form-control ${(textError??)?string('is-invalid','')}">
                <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" name="tag" value="<#if message??> ${message.tag} </#if>"
                       placeholder="введите тег" class="form-control">
                <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
            </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                    <input type="file" name="file" id="customFile" >
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Отправить</button>
            </div>

        </form>
    </div>
</div>

    <div class="card-columns">
        <#list messages as message>

        <div class="card my-3">

                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>

            <div class="m-2">
                <span>${message.text}</span>
                <i>${message.tag}</i>
             </div>
            <div class="card-footer text-muted">
                ${message.authorName}
            </div>
        </div>
<#else>
No messages
</#list>
    </div>

</@i.page>