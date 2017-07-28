package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum DriverType implements DriverSetup {

	FIREFOX {

		@Override
		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {

			return new FirefoxDriver(capabilities);
		}

		@Override
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			return capabilities;
		}

	},


	CHROME {

		@Override
		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {

			return new ChromeDriver(capabilities);
		}

		@Override
		public DesiredCapabilities getDesiredCapabilities() {

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--no default-browser-check"));
			HashMap<String, String> chromePreferences = new HashMap<String, String>();
			chromePreferences.put("profile.password_manager_enabled", "false");
			capabilities.setCapability("chrome.prefs", chromePreferences);
			return capabilities;
		}

	},
	
	PHANTOMJS {

		@Override
		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			
			return new PhantomJSDriver(capabilities);
		}

		@Override
		public DesiredCapabilities getDesiredCapabilities() {
			
			DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
			final List<String> cliArguments = new ArrayList<String>();
			cliArguments.add("--web-security=false");
			cliArguments.add("--ssl-protocol=any");
			cliArguments.add("--ignore-ssl-errors=true");
			capabilities.setCapability("phantomjs.cli.args", cliArguments);
			capabilities.setCapability("takesScreenshot", true);
			return capabilities;
		}
		
	}
}
