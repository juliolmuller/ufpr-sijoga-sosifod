
 $(document).ready(function(){   
    $('#filtro_login').on('change', function(){
      var value = $(this).val();
      console.log(value);
      if(value == "Advogado"){
        document.getElementById("btn_login").href = "advogado.html";
      }else if(value == "Juiz"){
        document.getElementById("btn_login").href = "juiz.html";
      }else{
        document.getElementById("btn_login").href = "parte.html";
      }
    });
});

 function logar(filtro){
  var value = filtro.value;
  if(value == "Advogado"){
    document.getElementById("btn_login").href = "advogado.html";
  }else if(value == "Juiz"){
    document.getElementById("btn_login").href = "juiz.html";
  }else{
    document.getElementById("btn_login").href = "parte.html";
  }
 }