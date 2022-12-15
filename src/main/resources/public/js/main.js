document.querySelector('#provincias').addEventListener('click',function(){
	obtenerDatos();
})

function obtenerDatos(){

    let url = 'https://ddstpa.com.ar/api/provincias';

    const api = new XMLHttpRequest();

    api.open('GET',url,true);
    api.send();

    api.onreadystatechange = function(){
        if (this.status == 200 && this.readyState == 4){

            console.log(this.responseText);

        }else{
            console.log("no pude entrar");
        }
    }
}