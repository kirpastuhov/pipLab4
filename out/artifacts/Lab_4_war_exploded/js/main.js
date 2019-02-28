
// Vue.component('v-select', VueSelect.VueSelect)
//
// new Vue({
//     el: '#app',
//     data: {
//         options: [
//             {id: 1, label: 1},
//             {id: 3, label: 3},
//             {id: 2, label: 2},
//         ],
//         selected: {id: 3, label: 2},
//     }
// })
// Select {'-5','-4','-3','-2','-1','0','1','2','3'
new Vue({
    el: '#R',
    data: function(){
        return {
            R : null,
            Rs: [
            '1','2','3', '4', '5'
            ],

        }
    }
})
new Vue({
    el: '#X',
    data: function(){
        return {
            X : null,
            Xs: [
                '-5','-4','-3','-2','-1','0','1','2','3'
            ],

        }
    }
})
