package com.wilp.samplepostapidemo.launcherapi;

import com.google.gson.annotations.SerializedName;

public class DashbaordData {
    @SerializedName("asset_count_by_status")
    private final AssetCountByStatus assetCountByStatus;

    @SerializedName("asset_count_data")
    private final AssetCountData assetCountData;

    public DashbaordData(AssetCountByStatus assetCountByStatus, AssetCountData assetCountData) {
        this.assetCountByStatus = assetCountByStatus;
        this.assetCountData = assetCountData;
    }

    public AssetCountByStatus getAssetCountByStatus() {
        return assetCountByStatus;
    }

    public AssetCountData getAssetCountData() {
        return assetCountData;
    }

    public static class AssetCountByStatus {
        @SerializedName("available_assets_count")
        private final int availableAssetsCount;

        @SerializedName("checkout_assets_count")
        private final int checkoutAssetsCount;

        @SerializedName("disposed_assets_count")
        private final int disposedAssetsCount;

        @SerializedName("lost_assets_count")
        private final int lostAssetsCount;

        public AssetCountByStatus(int availableAssetsCount, int checkoutAssetsCount, int disposedAssetsCount, int lostAssetsCount) {
            this.availableAssetsCount = availableAssetsCount;
            this.checkoutAssetsCount = checkoutAssetsCount;
            this.disposedAssetsCount = disposedAssetsCount;
            this.lostAssetsCount = lostAssetsCount;
        }

        public int getAvailableAssetsCount() {
            return availableAssetsCount;
        }

        public int getCheckoutAssetsCount() {
            return checkoutAssetsCount;
        }

        public int getDisposedAssetsCount() {
            return disposedAssetsCount;
        }

        public int getLostAssetsCount() {
            return lostAssetsCount;
        }
    }

    public static class AssetCountData {
        @SerializedName("total_assets_count")
        private final int totalAssetsCount;

        @SerializedName("total_cost_of_assets")
        private final String totalCostOfAssets;

        @SerializedName("asset_count_last_year")
        private final int assetCountLastYear;

        public AssetCountData(int totalAssetsCount, String totalCostOfAssets, int assetCountLastYear) {
            this.totalAssetsCount = totalAssetsCount;
            this.totalCostOfAssets = totalCostOfAssets;
            this.assetCountLastYear = assetCountLastYear;
        }

        public int getTotalAssetsCount() {
            return totalAssetsCount;
        }

        public String getTotalCostOfAssets() {
            return totalCostOfAssets;
        }

        public int getAssetCountLastYear() {
            return assetCountLastYear;
        }
    }
}
