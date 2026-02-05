package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import org.telegram.messenger.utils.BillingUtilities;
import org.telegram.tgnet.TLRPC;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class BillingController {
    public final static String PREMIUM_PRODUCT_ID = "telegram_premium";

    @Nullable
    public static Object PREMIUM_PRODUCT_DETAILS = null;

    private static BillingController instance;

    public static boolean billingClientEmpty = true;

    private final Map<String, Integer> currencyExpMap = new HashMap<>();

    public static BillingController getInstance() {
        if (instance == null) {
            instance = new BillingController(ApplicationLoader.applicationContext);
        }
        return instance;
    }

    private BillingController(Context ctx) {
        // No-op: billing client removed
    }

    public void setOnCanceled(Runnable onCanceled) {
        // No-op
    }

    public String getLastPremiumTransaction() {
        return null;
    }

    public String getLastPremiumToken() {
        return null;
    }

    public String formatCurrency(long amount, String currency) {
        return formatCurrency(amount, currency, getCurrencyExp(currency));
    }

    public String formatCurrency(long amount, String currency, int exp) {
        return formatCurrency(amount, currency, exp, false);
    }

    private static NumberFormat currencyInstance;
    private static NumberFormat currencyInstanceRounded;
    public String formatCurrency(long amount, String currency, int exp, boolean rounded) {
        if (currency == null || currency.isEmpty()) {
            return String.valueOf(amount);
        }
        if ("TON".equalsIgnoreCase(currency)) {
            return "TON " + (amount / 1_000_000_000.0);
        }
        if ("XTR".equalsIgnoreCase(currency)) {
            return "XTR " + LocaleController.formatNumber(amount, ',');
        }
        Currency cur = Currency.getInstance(currency);
        if (cur != null) {
            if (currencyInstance == null) {
                currencyInstance = NumberFormat.getCurrencyInstance();
            }
            currencyInstance.setCurrency(cur);
            if (rounded) {
                currencyInstance.setMaximumFractionDigits(0);
                currencyInstance.setMinimumFractionDigits(0);
                return currencyInstance.format(Math.round(amount / Math.pow(10, exp)));
            }
            final int defaultFractionDigits = cur.getDefaultFractionDigits();
            currencyInstance.setMinimumFractionDigits(defaultFractionDigits);
            currencyInstance.setMaximumFractionDigits(defaultFractionDigits);
            return currencyInstance.format(amount / Math.pow(10, exp));
        }
        return amount + " " + currency;
    }

    @SuppressWarnings("ConstantConditions")
    public int getCurrencyExp(String currency) {
        BillingUtilities.extractCurrencyExp(currencyExpMap);
        return currencyExpMap.getOrDefault(currency, 0);
    }

    public void startConnection() {
        // No-op: billing removed
    }

    public boolean isReady() {
        return false;
    }

    public boolean startManageSubscription(Context ctx, String productId) {
        return false;
    }

    public void addResultListener(String productId, Consumer<Object> listener) {
        // No-op
    }

    public void whenSetuped(Runnable listener) {
        // No-op
    }
}
