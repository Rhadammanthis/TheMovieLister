/**
 * 
 */
package me.hugomedina.themovielister.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * @author isaaclopez
 *
 */
public class CustomDialogProgress {
	
	private static boolean lollipop = false;
	
	private ProgressDialog api21;
	private MaterialDialog allApi;
	
	protected CustomDialogProgress(ProgressDialog api21, MaterialDialog allApi){
		this.api21 = api21;
		this.allApi = allApi;
	}
	
	/**
	 * @return el dialog
	 */
	public CustomDialogProgress show(){
		if(lollipop){
			api21.show();
		}else{
			allApi.show();
		}
		return this;
	}
	
	/**
	 * @param title el titulo
	 * @return el dialogo
	 */
	public CustomDialogProgress setTitle(String title){
		if(lollipop){
			api21.setTitle(title);
		}else{
			allApi.setTitle(title);
		}
		return this;
	}
	
	/**
	 * @param title el titulo
	 * @return el dialogo
	 */
	public CustomDialogProgress setTitle(int title){
		if(lollipop){
			api21.setTitle(title);
		}else{
			allApi.setContent(title);
		}
		return this;
	}
	
	/**
	 * @param message el mensaje
	 * @return el dialogo
	 */
	public CustomDialogProgress setMessage(String message){
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
	public CustomDialogProgress setMessage(int message){
		if(lollipop){
			api21.setMessage(api21.getContext().getString(message));
		}else{
			allApi.setContent(message);
		}
		return this;
	}
	
	/**
	 * @param progress el progreso en el que va
	 * @return el dialogo
	 */
	public CustomDialogProgress setProgress(int progress){
		if(lollipop){
			api21.setProgress(progress);
		}else{
			allApi.setProgress(progress);
		}
		return this;
	}
	
	/**
	 * @return el dialogo
	 */
	public CustomDialogProgress dismiss(){
		if(lollipop){
			api21.dismiss();
		}else{
			allApi.dismiss();
		}
		return this;
	}
	
	/**
	 * @param cancel si es cancelable
	 * @return el custondialogprogress
	 */
	public CustomDialogProgress setCancelable(boolean cancel){
		if(lollipop){
			api21.setCancelable(cancel);
		}else{
			allApi.setCancelable(cancel);
		}
		return this;
	}
	
	
	/**
	 * @author isaaclopez
	 *
	 */
	public static class Builder{
		
		private ProgressDialog builderA;
		private MaterialDialog.Builder bulderM;
		private Context context;
		
		/**
		 * @param context el contexto
		 */
		public Builder(Context context){
			lollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
			if(lollipop){
				builderA = new ProgressDialog(context);
			}else{
				bulderM = new MaterialDialog.Builder(context);
			}
			this.context = context;
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
		public Builder setMessage(int message){
			if(lollipop){
				builderA.setMessage(context.getString(message));
			}else{
				bulderM.content(message);
			}
			return this;
		}
		
		/**
		 * @param cancel si es cancelable
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
		 * @param indeterminate si es infinito el progreso
		 * @param maxprogress el maximo del progreso
		 * @return el Builder
		 */
		public Builder setProgress(boolean indeterminate,int maxprogress){
			if(lollipop){
				builderA.setIndeterminate(false);
				builderA.setProgress(maxprogress);
			}else{
				bulderM.progress(indeterminate, maxprogress);
			}
			return this;
		}
		
		/**
		 * @return la instancia del dialogo
		 */
		@SuppressLint("NewApi")
		public CustomDialogProgress create(){
			ProgressDialog api21 = null;
			MaterialDialog allApi = null;
			if(lollipop){
				builderA.create();
				api21 = builderA;
			}else{
				allApi = bulderM.build();
			}
			return new CustomDialogProgress(api21,allApi);
		}
		
		/**
		 * @return el dialogo que se esta mostrando
		 */
		public CustomDialogProgress show(){
			CustomDialogProgress dialog = create();
			dialog.show();
			return dialog;
		}
		
	}

}
