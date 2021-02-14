(ns dharrigan.main
  (:require
   [google-apps-clj.google-drive :refer [list-files!]]
   [google-apps-clj.credentials :as auth]))

(defn login
  [config]
  (auth/get-auth-map config ["https://www.googleapis.com/auth/drive"]))

(defn list-my-files
  [credentials folder-id]
  (list-files! credentials folder-id))

(comment

 ; require in my namespace, as we're in a comment block, for experimentation (Rich Comment Form)
 (require '[dharrigan.main :as m])

 ; define my credentials, first part
 (def initial-credentials {:client-id "YOUR CLIENT ID"
                           :client-secret "YOUR CLIENT SECRET"
                           :redirect-uris ["urn:ietf:wg:oauth:2.0:oob"]})

 ; do a login, follow the instructions on the output to open up a browser, grant permissions to the application
 ; the result, a map is returned.
 (m/login initial-credentials)

 ; take the map and make sure you plug in the auth-map below, TAKE CARE to change "access_token" to "access-token" and so on
 ; basically underscores to hypens

 ; define my credentials, first part
 (def credentials {:client-id "YOUR CLIENT ID"
                   :client-secret "YOUR CLIENT SECRET"
                   :redirect-uris ["urn:ietf:wg:oauth:2.0:oob"]
                   :auth-map {:access-token "YOUR ACCESS TOKEN"
                              :expires-in 3599
                              :refresh-token "YOUR REFRESH TOKEN"
                              :scope "https://www.googleapis.com/auth/drive"
                              :token-type "Bearer"}})

 ;; now list the files in the folder, passing in your credentials and the folder id
 ;; "PUT YOUR FOLDER ID HERE, WHICH CAN BE FOUND AS THE LAST SEGMENT OF THE URL, i.e., https://drive.google.com/drive/u/0/folders/<HERE IS THE ID>"
 (m/list-my-files credentials "YOUR FOLDER ID")

 ;; result (with my sensitive data scrubbed)
 ;;
;; [{:created-date
;;   #object[com.google.api.client.util.DateTime 0x4682faeb "2021-02-14T08:34:59.245Z"],
;;   :original-filename "foo.txt",
;;   :user-permission
;;   {:etag "",
;;    :id "me",
;;    :kind "drive#permission",
;;    :role "owner",
;;    :self-link
;;    "https://www.googleapis.com/drive/v2/files/FOO",
;;    :type "user"},
;;   :capabilities
;;   {:can-trash true,
;;    :can-share true,
;;    :can-move-item-into-team-drive true,
;;    :can-add-children nil,
;;    :can-move-item-out-of-drive true,
;;    :can-delete true,
;;    :can-edit true,
;;    :can-modify-content true,
;;    :can-remove-children nil,
;;    :can-untrash true,
;;    :can-copy true,
;;    :can-add-my-drive-parent nil,
;;    :can-move-children-within-drive nil,
;;    :can-remove-my-drive-parent true,
;;    :can-change-copy-requires-writer-permission true,
;;    :can-read-revisions true,
;;    :can-list-children nil,
;;    :can-move-item-within-drive true,
;;    :can-download true,
;;    :can-rename true,
;;    :can-change-restricted-download true,
;;    :can-comment true},
;;   :owned-by-me true,
;;   :labels
;;   {:hidden nil,
;;    :restricted nil,
;;    :starred nil,
;;    :trashed nil,
;;    :viewed true,
;;    :modified true},
;;   :explicitly-trashed nil,
;;   :last-modifying-user
;;   {:display-name "",
;;    :email-address "",
;;    :is-authenticated-user true,
;;    :kind "drive#user",
;;    :permission-id "",
;;    :picture
;;    {:url
;;     ""}},
;;   :permissions
;;   [{:role "owner",
;;     :deleted nil,
;;     :name "",
;;     :photo-link
;;     "",
;;     :type "user",
;;     :etag "\"\"",
;;     :id "",
;;     :kind "drive#permission",
;;     :email-address "",
;;     :domain "",
;;     :self-link
;;     ""}],
;;   :owners
;;   [{:display-name "",
;;     :email-address "",
;;     :is-authenticated-user true,
;;     :kind "drive#user",
;;     :permission-id "",
;;     :picture
;;     {:url
;;      ""}}],
;;   :download-url
;;   "",
;;   :modified-by-me-date
;;   #object[com.google.api.client.util.DateTime 0x141b4976 "2021-02-14T08:34:48.000Z"],
;;   :editable true,
;;   :copy-requires-writer-permission nil,
;;   :marked-viewed-by-me-date
;;   #object[com.google.api.client.util.DateTime 0x27eb332c "1970-01-01T00:00:00.000Z"],
;;   :mime-type "text/plain",
;;   :last-modifying-user-name "",
;;   :copyable true,
;;   :thumbnail-version "1",
;;   :has-thumbnail true,
;;   :app-data-contents nil,
;;   :full-file-extension "txt",
;;   :embed-link
;;   "https://drive.google.com/file/d/",
;;   :icon-link
;;   "",
;;   :etag "",
;;   :quota-bytes-used 12,
;;   :web-content-link
;;   "",
;;   :file-size 12,
;;   :last-viewed-by-me-date
;;   #object[com.google.api.client.util.DateTime 0x760fd3f8 "2021-02-14T08:34:59.245Z"],
;;   :shareable true,
;;   :title "foo.txt",
;;   :thumbnail-link
;;   "https://lh3.googleusercontent.com/",
;;   :spaces ["drive"],
;;   :id "",
;;   :kind "drive#file",
;;   :alternate-link
;;   "https://drive.google.com/file/d/",
;;   :parents
;;   [{:id "",
;;     :is-root nil,
;;     :kind "drive#parentReference",
;;     :parent-link
;;     "",
;;     :self-link
;;     ""}],
;;   :permission-ids [""],
;;   :file-extension "txt",
;;   :shared nil,
;;   :head-revision-id
;;   "",
;;   :can-read-revisions true,
;;   :writers-can-share true,
;;   :modified-date
;;   #object[com.google.api.client.util.DateTime 0x4d3f0ced "2021-02-14T08:34:48.000Z"],
;;   :is-app-authorized nil,
;;   :version 2,
;;   :owner-names [""],
;;   :md5-checksum "",
;;   :can-comment true,
;;   :self-link
;;   ""}]

 ,)
