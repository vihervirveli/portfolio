

let autodata = [20,30,50];
let autonames = ["Ford","Opel","Toyota"];


function addCar(n){
    autodata[n]++;
    chart.update();
}

function nollaa(){
    autodata[0]= 0;
    autodata[1]= 0;
    autodata[2]= 0;
    chart.update();
}

function drawChart(){
    var ctx = document.getElementById("myChart").getContext('2d');
    chart = new Chart(ctx, {
    // The type of chart we want to create
//    type: 'horizontalBar',
    type: 'bar',

    // The data for our dataset
    data: {
        labels: autonames,
        datasets: [{
            label: "Lukumäärä (kpl)",
            data: autodata,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)'
               
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },

    // Configuration options go here
    options: {
        legend: {display:false},
        title: {display:true, text:"Autojen lukumäärä"},
   responsive: false,
        scales: {
            yAxes: [{
                barThickness: 53,
                ticks: {
                    beginAtZero:true
                }
            }],
            xAxes: [{
                barThickness: 113
            }]
        }
   }
});
}


