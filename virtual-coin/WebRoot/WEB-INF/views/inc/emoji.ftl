  <div class="am-modal am-modal-no-btn" tabindex="-1" id="popEmoji">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">表情
      <span data-am-modal-close class="am-close" style="opacity:1"><strong>X</strong></span>
    </div>
    <div class="am-modal-bd" id="modalEmoji">
        <ul class="am-avg-sm-7 am-avg-md-7 am-avg-lg-7 am-thumbnails">
        <#list 1..56 as t>
          <#if t lt 10>
          <li><img class="am-thumbnail am-emoji" src="images/2.0/emoji/70${t}.png" onclick="chooseEmoji('70${t}.png')" /></li>
          <#else>
          <li><img class="am-thumbnail am-emoji" src="images/2.0/emoji/7${t}.png" onclick="chooseEmoji('7${t}.png')" /></li>
          </#if>
        </#list>
        </ul>
    </div>
  </div>
  </div>
  