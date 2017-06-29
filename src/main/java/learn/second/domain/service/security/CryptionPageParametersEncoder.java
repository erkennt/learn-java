package learn.second.domain.service.security;

import java.io.UnsupportedEncodingException;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.Url.QueryParameter;
import org.apache.wicket.request.mapper.parameter.IPageParametersEncoder;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CryptionPageParametersEncoder implements IPageParametersEncoder {

	@Override
	public PageParameters decodePageParameters(Url url) {

		PageParameters parameters = new PageParameters();

		int i = 0;
		for (String s : url.getSegments()) {
			parameters.set(i, s);
			++i;
		}

		// 各パラメーターの復号
		for (QueryParameter p : url.getQueryParameters()) {

			try {
				parameters.add(EncodePassword.base64Decode(p.getName()), EncodePassword.base64Decode(p.getValue()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return parameters.isEmpty() ? null : parameters;
	}

	@Override
	public Url encodePageParameters(PageParameters pageParameters) {

		Url url = new Url();

		for (int i = 0; i < pageParameters.getIndexedCount(); ++i) {
			url.getSegments().add(pageParameters.get(i).toString());
		}
		// 各パラメーターを暗号化
		for (PageParameters.NamedPair pair : pageParameters.getAllNamed()) {

			String encryptedKey = EncodePassword.base64Encode(pair.getKey().getBytes());
			String encryptedValue = EncodePassword.base64Encode(pair.getValue().getBytes());

			QueryParameter param = new QueryParameter(encryptedKey, encryptedValue);
			url.getQueryParameters().add(param);
		}
		return url;
	}
}