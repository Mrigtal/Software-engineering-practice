SessionStorage = {
    get: function (key) {
        var v = localStorage.getItem(key);
        if (v && typeof (v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        localStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        localStorage.removeItem(key);
    },
    clearAll: function () {
        localStorage.clear();
    }
};
