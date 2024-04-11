<script setup>
import {onMounted, defineProps, defineEmits} from 'vue';
import axios from "axios";

const props = defineProps(['cartItems', 'total'])
const emits = defineEmits(['clearCart'])

// 在组件挂载后加载 PayPal SDK
onMounted(() => {
    const script = document.createElement('script');
    script.src = "https://www.paypal.com/sdk/js?" +
        "client-id=AeuX3h6lOAafYPzM_zaXGck78bYIlk4LXlBR4ADD3uwZddGjq7_QuNDnyxdF4Qp4ffo4DyT3OvJQ8nKF" +
        "&currency=USD";
    script.onload = () => {
        // eslint-disable-next-line no-undef
        paypal.Buttons({
            style: {
                layout: 'vertical',
                color: 'gold',
                shape: 'pill',
                label: 'checkout',
                size: 'medium'
            },
            createOrder: function (data, actions) {
                return actions.order.create({
                    purchase_units: [{
                        amount: {
                            value: props.total,
                            currency_code: 'USD',
                        }
                    }]
                })
            },
            onApprove: function (data, actions) {
                return actions.order.capture().then(function (details) {
                    console.log('Transaction completed by ' + details.payer.name.given_name);
                    // Send cartItems and total to the backend
                    axios.post('/api/user/payment', props.cartItems).then(function (response) {
                        console.log(response);
                        emits('clearCart')
                    }).catch(function (error) {
                        console.error(error);
                    });
                });
            },
            onCancel: function (data) {
                console.log('Payment cancelled', data);
            }
        }).render('#paypal-button-container');

    };
    document.body.appendChild(script);
});
</script>

<template>
    <div id="paypal-button-container"></div>
</template>

<style scoped>
#paypal-button-container {
    width: 250px;
}
</style>
