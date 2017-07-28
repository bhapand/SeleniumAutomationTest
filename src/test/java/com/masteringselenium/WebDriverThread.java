package com.masteringselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import config.DriverType;


public class WebDriverThread {

	private WebDriver webdriver;
	private DriverType selectedDriverType;

	private final DriverType defaultDriverType = DriverType.FIREFOX;
	private final String browser = System.getProperty("browser").toUpperCase();
	private final String operatingSystem = System.getProperty("os.name").toUpperCase();
	private final String systemArchitechture = System.getProperty("os.arch").toUpperCase();

	public WebDriver getDriver(){
		if(null==webdriver){

			selectedDriverType = determineEffectiveDriverType();
			DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
			instantiateWebDriver(desiredCapabilities);
		}
		return webdriver;
	}

	private WebDriver instantiateWebDriver(DesiredCapabilities desiredCapabilities) {
		System.out.println(" ");
		System.out.println("Current Operating System: " +operatingSystem);
		System.out.println("Current Architecture: "+systemArchitechture);
		System.out.println("Current browser selection: "+browser);
		return webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
	}

	private DriverType determineEffectiveDriverType() {
		DriverType driverType = defaultDriverType;
		try{
			driverType = DriverType.valueOf(browser);
		}catch (IllegalArgumentException ignored) {
			System.err.println("Unknown driver specified, defaulting to "+driverType + "---");

		}catch(NullPointerException ignored){
			System.err.println("No driver specified, defaulting to "+ selectedDriverType + "---");

		}
		return driverType;
	}

	public void quitDriver(){
		if(null !=webdriver){
			webdriver.quit();
			webdriver = null;
		}
	}
}
