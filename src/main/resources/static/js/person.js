function file_changed(){
	//照片選取
  var selectedFile = document.getElementById('image').files[0];
  var img = document.getElementById('img')
  var reader = new FileReader();
  reader.onload = function(){
	  if(this.result!=null){
		  img.src = this.result  
	  }
  }
  reader.readAsDataURL(selectedFile);
	 }

function checkEmptyValue(){
	if($('#username').val()== ''){
		alert("請輸入姓名");
	}
}

$(document).ready(function(){
	
	//$(".container").fadeIn(3000);
	
	$("#sidebarCollapse").on('click',function(){
		$("#sidebar").toggleClass('active');
	});
	
	$("#sidebarCollapse").animate({left: '-20px'}, "slow");

	var get_person='/api/v1/GET/person'
	
	//取得使用者資料
	$.ajax({
	    type: "GET",
	    url: get_person,
	    dataType: "json",
	    success: function (response) {
	    	
	    	if(response.image!=null){
	    		$('#img').attr('src', 'data:image/png;base64,'+response.image);
	    	}
	    	
	    	if(response.username!=null){
	    		$('#username').val(response.username);
	    	}
	    	
	    	if(response.sex!=null){
	    		$('#sex').val(response.sex);
	    	}
	    	
	    	if(response.height!=null){
	    		$('#height').val(response.height);
	    	}
	    	if(response.weight!=null){
	    	$('#weight').val(response.weight);
	    	}
	    	
	    	if(response.position!=null){
			    switch (response.position) {
		    	  case 1:
		    		  $('#position').val('1');
		    	    break;
		    	  case 2:
		    		  $('#position').val('2');
			    	    break;
		    	  case 3:
		    		  $('#position').val('3');
			    	    break;
		    	  case 4:
		    		  $('#position').val('4');
			    	    break;
		    	  case 5:
		    		  $('#position').val('5');
			    	    break;
		    	  default:
		    	    console.log('position not found');
		    	}
	    	}
	    },
	    error: function (thrownError) {
	      console.log(thrownError);
	    }
	  });
});