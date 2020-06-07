var HideNotifications = {
    enable: function(successCallback, errorCallback) {
        errorCallback = errorCallback || this.errorCallback;
        cordova.exec(successCallback, errorCallback, 'HideNotifications', 'enable', []);
    },

    disable: function(successCallback, errorCallback) {
        errorCallback = errorCallback || this.errorCallback;
        cordova.exec(successCallback, errorCallback, 'HideNotifications', 'disable', []);
    },

    getStatus: function(successCallback, errorCallback) {
        errorCallback = errorCallback || this.errorCallback;
        cordova.exec(successCallback, errorCallback, 'HideNotifications', 'getStatus', []);
    },

    errorCallback: function() {
        console.log("WARNING: BlockCalls errorCallback not implemented");
    }
};

module.exports = HideNotifications;
