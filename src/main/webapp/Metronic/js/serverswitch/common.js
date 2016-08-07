//光标停在文字最后
function focusLast(obj)
{
 var r = obj.createTextRange();
 r.moveStart('character',obj.value.length);
 r.collapse(true);
 r.select();
}

String.prototype.trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function()
{
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function()
{
    return this.replace(/(\s*$)/g, "");
}
