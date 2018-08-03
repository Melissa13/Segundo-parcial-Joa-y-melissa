
package soap.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Postear_QNAME = new QName("http://ws.SOAP/", "Postear");
    private final static QName _GetUserPosts_QNAME = new QName("http://ws.SOAP/", "getUserPosts");
    private final static QName _PostearResponse_QNAME = new QName("http://ws.SOAP/", "PostearResponse");
    private final static QName _GetUserPostsResponse_QNAME = new QName("http://ws.SOAP/", "getUserPostsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Postear }
     * 
     */
    public Postear createPostear() {
        return new Postear();
    }

    /**
     * Create an instance of {@link GetUserPosts }
     * 
     */
    public GetUserPosts createGetUserPosts() {
        return new GetUserPosts();
    }

    /**
     * Create an instance of {@link PostearResponse }
     * 
     */
    public PostearResponse createPostearResponse() {
        return new PostearResponse();
    }

    /**
     * Create an instance of {@link GetUserPostsResponse }
     * 
     */
    public GetUserPostsResponse createGetUserPostsResponse() {
        return new GetUserPostsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Postear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.SOAP/", name = "Postear")
    public JAXBElement<Postear> createPostear(Postear value) {
        return new JAXBElement<Postear>(_Postear_QNAME, Postear.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserPosts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.SOAP/", name = "getUserPosts")
    public JAXBElement<GetUserPosts> createGetUserPosts(GetUserPosts value) {
        return new JAXBElement<GetUserPosts>(_GetUserPosts_QNAME, GetUserPosts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostearResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.SOAP/", name = "PostearResponse")
    public JAXBElement<PostearResponse> createPostearResponse(PostearResponse value) {
        return new JAXBElement<PostearResponse>(_PostearResponse_QNAME, PostearResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserPostsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.SOAP/", name = "getUserPostsResponse")
    public JAXBElement<GetUserPostsResponse> createGetUserPostsResponse(GetUserPostsResponse value) {
        return new JAXBElement<GetUserPostsResponse>(_GetUserPostsResponse_QNAME, GetUserPostsResponse.class, null, value);
    }

}
