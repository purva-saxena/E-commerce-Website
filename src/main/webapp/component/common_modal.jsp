<%-- 
    Document   : common_modal
    Created on : 10-Jan-2021, 9:33:54 pm
    Author     : hinas
--%>

<!-- Modal -->
<div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header modal-lg custom-bg">
        <h5 class="modal-title" id="exampleModalLabel">My Cart</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="cart-body">
              
              
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" onclick="GoToCheckOut()" class="btn btn custom-bg checkout-btn">Check Out</button>
      </div>
    </div>
  </div>
</div>


<div id="toast">This is our custom toast text</div>