let emailRE = /./;
let dotsRE = /(\d+(\.\d+)?)/;

// Setup Firebase
let config = {
    apiKey: "AIzaSyAi_yuJciPXLFr_PYPeU3eTvtXf8jbJ8zw",
    authDomain: "vue-demo-537e6.firebaseapp.com",
    databaseURL: "https://vue-demo-537e6.firebaseio.com"
}
// firebase.initializeApp(config);

// create Vue app
let app = new Vue({
    // element to mount to
    el: '#app',
    // initial data
    data: {
        newUser: {
            name: '',
            email: ''
        }
    },
    computed: {
        validation: function () {
            return {
                name: !!this.newUser.name.trim(),
                email: emailRE.test(this.newUser.email)
            }
        },
        isValid: function () {
            var validation = this.validation
            return Object.keys(validation).every(function (key) {
                return validation[key]
            })
        }
    },
    // methods
    methods: {
        addUser: function () {
            if (this.isValid) {
                // usersRef.push(this.newUser)
                console.log(this.newUser.name);
                console.log(this.newUser.email);
            }
        }
    }
});
let register = new Vue({
    // element to mount to
    el: '#register',
    // initial data
    data: {
        newUser: {
            name: '',
            email: ''
        }
    },
   computed: {
        validation: function () {
            return {
                name: !!this.newUser.name.trim(),
                email: emailRE.test(this.newUser.email)
            }
        },
        isValid: function () {
            let validation = this.validation
            return Object.keys(validation).every(function (key) {
                return validation[key]
            })
        }
    },
    // methods
    methods: {
        addUser: function () {
            if (this.isValid) {
                // usersRef.push(this.newUser)
                console.log(this.newUser.name);
                console.log(this.newUser.email);
            }
        }
    }
});
let dots = new Vue({

    // element to mount to
    el: '#dots',
    // initial data
    data: {
        newDot: {
            x: '',
            y: '',
            r: ''
        }
    },
   computed: {
        validation: function () {
            return {
                x: dotsRE.test(this.newDot.x),
                y: dotsRE.test(this.newDot.y),
                r: dotsRE.test(this.newDot.r)
            }
        },
        isValid: function () {
            let validation = this.validation
            return Object.keys(validation).every(function (key) {
                return validation[key]
            })
        }
    },
    // methods
    methods: {
        addUser: function () {
            if (this.isValid) {;
            }
        }
    }
});
