<#ftl encoding='UTF-8'>

<#macro html title>

    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
    </head>
    <body>
    <#nested>
    </body>
    </html>

</#macro>