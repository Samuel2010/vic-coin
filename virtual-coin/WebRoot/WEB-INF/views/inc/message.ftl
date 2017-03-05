  <div class="am-modal am-modal-no-btn" tabindex="-1" id="msg-modal">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示
      <span data-am-modal-close class="am-close" style="opacity:1"><strong>X</strong></span>
    </div>
    <div class="am-modal-bd" id="modalMsg"></div>
  </div>
  </div>
  

<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
  <div class="am-modal-dialog">
    <div class="am-modal-hd" style="font-weight:bold;">提示</div>
    <div class="am-modal-bd" id="alertMsg">
      
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>

<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示</div>
    <div class="am-modal-bd"  id="confirmMsg"> 
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
    </div>
  </div>
</div>

  
  <div class="am-popup" id="msg-popup">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title" id="popMsgTitle">温馨提示</h4>
      <span data-am-modal-close class="am-close" style="opacity:1"><span class="am-text-danger">X</span></span>
    </div>
    <div class="am-popup-bd" id="popMsg"></div>
    <button type="button" class="am-btn am-btn-secondary am-center" data-am-modal-close>关闭</button>
  </div>
  </div>
  
  <script type="text/javascript" src="<@s.url '/js/2.0/common.js?time=${.now}'/>"></script>
  
