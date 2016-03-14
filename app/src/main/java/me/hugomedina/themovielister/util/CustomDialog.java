/**
 * 
 */
package me.hugomedina.themovielister.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;

import me.hugomedina.themovielister.MovieListerApplication;

/**
 * @author isaaclopez
 *
 */
public class CustomDialog {
	
	private static boolean lollipop = MovieListerApplication.isLollipop;
	
	private AlertDialog api21;
	private MaterialDialog allApi;
	
	protected CustomDialog(AlertDialog api21, MaterialDialog allApi){
		this.api21 = api21;
		this.allApi = allApi;
	}
	
	/**
	 * @return el dialog
	 */
	public CustomDialog show(){
		if(lollipop){
			api21.show();
		}else{
			allApi.show();
		}
		return this;
	}
	
	/**
	 * @param message el mensaje
	 * @return el dialogo
	 */
	public CustomDialog setMessage(String message){
		if(lollipop){
			api21.setMessage(message);
		}else{
			allApi.setContent(message);
		}
		return this;
	}
	
	/**
	 * @param message el mensaje
	 * @return el dialogo
	 */
	public CustomDialog setMessage(int message){
		if(lollipop){
			api21.setMessage(api21.getContext().getString(message));
		}else{
			allApi.setContent(message);
		}
		return this;
	}
	
	
	/**
	 * @author isaaclopez
	 *
	 */
	public static class Builder{
		
		private AlertDialog.Builder builderA;
		private MaterialDialog.Builder bulderM;
		
		/**
		 * @param context el contexto
		 */
		public Builder(Context context){
			if(lollipop){
				builderA = new AlertDialog.Builder(context);
			}else{
				bulderM = new MaterialDialog.Builder(context);
			}
		}
		
		/**
		 * @param title el titulo
		 * @return Builder
		 */
		public Builder setTitle(String title){
			if(lollipop){
				builderA.setTitle(title);
			}else{
				bulderM.title(title);
			}
			return this;
		}
		
		/**
		 * @param title el titulo
		 * @return Builder
		 */
		public Builder setTitle(int title){
			if(lollipop){
				builderA.setTitle(title);
			}else{
				bulderM.title(title);
			}
			return this;
		}
		
		/**
		 * @param message el mensaje
		 * @return Builder
		 */
		public Builder setMessage(String message){
			if(lollipop){
				builderA.setMessage(message);
			}else{
				bulderM.content(message);
			}
			return this;
		}
		
		/**
		 * @param message el mensaje
		 * @return Builder
		 */
		public Builder setMessage(@IdRes int message){
			if(lollipop){
				builderA.setMessage(message);
			}else{
				bulderM.content(message);
			}
			return this;
		}
		
		/**
		 * @param name el nombre
		 * @param listener el listener
		 * @return Builder
		 */
		public Builder setPositiveButtom(String name,final CallbackListener listener){
			if(lollipop){
				builderA.setPositiveButton(name, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(listener!=null){
							listener.onClick();
						}
					}
				});
			}else{
				bulderM.positiveText(name);
				if(listener!=null){
					bulderM.onPositive(new SingleButtonCallback() {
						
						@Override
						public void onClick(@NonNull MaterialDialog dialog,
								@NonNull DialogAction which) {
							listener.onClick();
						}
					});
				}
			}
			return this;
		}
		
		/**
		 * @param name el nombre
		 * @param listener el listener
		 * @return Builder
		 */
		public Builder setNegativeButtom(String name,final CallbackListener listener){
			if(lollipop){
				builderA.setNegativeButton(name, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(listener!=null){
							listener.onClick();
						}
					}
				});
			}else{
				bulderM.negativeText(name);
				if(listener!=null){
					bulderM.onNegative(new SingleButtonCallback() {
						
						@Override
						public void onClick(@NonNull MaterialDialog dialog,
								@NonNull DialogAction which) {
							listener.onClick();
						}
					});
				}
			}
			return this;
		}
		
		/**
		 * @param name el nombre
		 * @param listener el listener
		 * @return Builder
		 */
		public Builder setNeutralButtom(String name,final CallbackListener listener){
			if(lollipop){
				builderA.setNeutralButton(name, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(listener!=null){
							listener.onClick();
						}
					}
				});
			}else{
				bulderM.neutralText(name);
				if(listener!=null){
					bulderM.onNeutral(new SingleButtonCallback() {
						
						@Override
						public void onClick(@NonNull MaterialDialog dialog,
								@NonNull DialogAction which) {
							listener.onClick();
						}
					});
				}
			}
			return this;
		}
		
		/**
		 * @param name el nombre
		 * @param listener el listener
		 * @return Builder
		 */
		public Builder setPositiveButtom(@IdRes int name,final CallbackListener listener){
			if(lollipop){
				builderA.setPositiveButton(name, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(listener!=null){
							listener.onClick();
						}
					}
				});
			}else{
				bulderM.positiveText(name);
				if(listener!=null){
					bulderM.onPositive(new SingleButtonCallback() {
						
						@Override
						public void onClick(@NonNull MaterialDialog dialog,
								@NonNull DialogAction which) {
							listener.onClick();
						}
					});
				}
			}
			return this;
		}
		
		/**
		 * @param name el nombre
		 * @param listener el listener
		 * @return Builder
		 */
		public Builder setNegativeButtom(@IdRes int name,final CallbackListener listener){
			if(lollipop){
				builderA.setNegativeButton(name, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(listener!=null){
							listener.onClick();
						}
					}
				});
			}else{
				bulderM.negativeText(name);
				if(listener!=null){
					bulderM.onNegative(new SingleButtonCallback() {
						
						@Override
						public void onClick(@NonNull MaterialDialog dialog,
								@NonNull DialogAction which) {
							listener.onClick();
						}
					});
				}
			}
			return this;
		}
		
		/**
		 * @param name el nombre
		 * @param listener el listener
		 * @return Builder
		 */
		public Builder setNeutralButtom(@IdRes int name,final CallbackListener listener){
			if(lollipop){
				builderA.setNeutralButton(name, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(listener!=null){
							listener.onClick();
						}
					}
				});
			}else{
				bulderM.neutralText(name);
				if(listener!=null){
					bulderM.onNeutral(new SingleButtonCallback() {
						
						@Override
						public void onClick(@NonNull MaterialDialog dialog,
								@NonNull DialogAction which) {
							listener.onClick();
						}
					});
				}
			}
			return this;
		}
		
		/**
		 * @param cancel boolean
		 * @return Builder
		 */
		public Builder setCancelable(boolean cancel){
			if(lollipop){
				builderA.setCancelable(cancel);
			}else{
				bulderM.cancelable(cancel);
			}
			return this;
		}
		
		/**
		 * @return la instancia del dialogo
		 */
		public CustomDialog create(){
			AlertDialog api21 = null;
			MaterialDialog allApi = null;
			if(lollipop){
				api21 = builderA.create();
			}else{
				allApi = bulderM.build();
			}
			return new CustomDialog(api21,allApi);
		}
		
		/**
		 * @return el dialogo que se esta mostrando
		 */
		public CustomDialog show(){
			CustomDialog dialog = create();
			dialog.show();
			return dialog;
		}
		
	}
	
	/**
	 * @author isaaclopez
	 *
	 */
	public interface CallbackListener{
		/**
		 * Click de la funcion
		 */
		void onClick();
	}

}
