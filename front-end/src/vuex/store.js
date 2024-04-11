import { createStore } from 'vuex';

export default createStore({
    state: {
        cart: []
    },
    mutations: {
        ADD_TO_CART(state, product) {
            const existingProduct = state.cart.find(item => item.pid === product.pid);
            if (existingProduct) {
                existingProduct.quantity += 1;
            } else {
                state.cart.push({ ...product, quantity: 1 });
            }
        },
        DECREASE_QUANTITY(state, item) {
            if (item.quantity > 0) {
                item.quantity--;
                if (item.quantity === 0) {
                    state.cart = state.cart.filter(i => i.pid !== item.pid);
                }
            }
        },
        INCREASE_QUANTITY(state, item) {
            item.quantity++;
        },
        REMOVE_FROM_CART(state, item) {
            state.cart = state.cart.filter(i => i.pid !== item.pid);
        },
        CLEAR_CART(state) {
            state.cart = []
        }
    },
    actions: {
        addToCart({ commit }, product) {
            commit('ADD_TO_CART', product);
        },
        decreaseQuantity({ commit }, item) {
            commit('DECREASE_QUANTITY', item);
        },
        increaseQuantity({ commit }, item) {
            commit('INCREASE_QUANTITY', item);
        },
        removeFromCart({ commit }, item) {
            commit('REMOVE_FROM_CART', item);
        },
        clearCart({ commit }) {
            commit('CLEAR_CART')
        }
    },
    getters: {
        totalPrice: state => {
            return state.cart.reduce((total, item) => total + (item.price * item.quantity), 0);
        }
    }
});
