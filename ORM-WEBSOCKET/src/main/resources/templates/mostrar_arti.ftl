
<#list listArti as item>
<div class="post-preview">
    <h2 class="post-title">  ${item.title}  </h2>
    <h5> Posted by:  ${item.getAuthor().username} </h5>
    <h5><span class="glyphicon glyphicon-time"> </span> ${item.date}  </h5>
    <h5><span class="glyphicon glyphicon-tags"></span> ${item.getStringTags()} </h5>
    <a href="/article/${item.id}/"> ${item.title}</a>
    <p>  ${item.body?substring(0,70)} </p>
    <br><br>
</div>
</#list>
