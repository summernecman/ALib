package com.android.lib.bean;

//by summer on 2017-06-20.

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.AsyncModel;
import com.raizlabs.android.dbflow.structure.InvalidDBConfiguration;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.io.Serializable;


public class BaseBean implements Serializable, Observable ,Model {

    private transient PropertyChangeRegistry mCallbacks;

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }


    /**
     * Specifies the Action that was taken when data changes
     */
    public enum Action {

        /**
         * The model called {@link Model#save()}
         */
        SAVE,

        /**
         * The model called {@link Model#insert()}
         */
        INSERT,

        /**
         * The model called {@link Model#update()}
         */
        UPDATE,

        /**
         * The model called {@link Model#delete()}
         */
        DELETE,

        /**
         * The model was changed. used in prior to {@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR1}
         */
        CHANGE
    }

    @ColumnIgnore
    private transient ModelAdapter modelAdapter;

    @Override
    public void load() {
        getModelAdapter().load(this);
    }

    @Override
    public void load(@NonNull DatabaseWrapper wrapper) {
        getModelAdapter().load(this, wrapper);
    }

    @Override
    public boolean save() {
        return getModelAdapter().save(this);
    }


    @Override
    public boolean save(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().save(this, databaseWrapper);
    }

    @Override
    public boolean delete() {
        return getModelAdapter().delete(this);
    }

    @Override
    public boolean delete(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().delete(this, databaseWrapper);
    }

    @Override
    public boolean update() {
        return getModelAdapter().update(this);
    }

    @Override
    public boolean update(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().update(this, databaseWrapper);
    }

    @Override
    public long insert() {
        return getModelAdapter().insert(this);
    }

    @Override
    public long insert(DatabaseWrapper databaseWrapper) {
        return getModelAdapter().insert(this, databaseWrapper);
    }

    @Override
    public boolean exists() {
        return getModelAdapter().exists(this);
    }

    @Override
    public boolean exists(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().exists(this, databaseWrapper);
    }

    @NonNull
    @Override
    public AsyncModel<? extends Model> async() {
        return new AsyncModel<>(this);
    }

    /**
     * @return The associated {@link ModelAdapter}. The {@link FlowManager}
     * may throw a {@link InvalidDBConfiguration} for this call if this class
     * is not associated with a table, so be careful when using this method.
     */
    public ModelAdapter getModelAdapter() {
        if (modelAdapter == null) {
            modelAdapter = FlowManager.getModelAdapter(getClass());
        }
        return modelAdapter;
    }
}
