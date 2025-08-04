import React from 'react';

const Post = ({ title, body }) => {
  return (
    <div style={{ border: '1px solid #ccc', margin: '10px 0', padding: '15px', borderRadius: '8px', backgroundColor: '#f9f9f9' }}>
      <h3 style={{ color: '#333', marginBottom: '10px' }}>{title}</h3>
      <p style={{ color: '#666', lineHeight: '1.6' }}>{body}</p>
    </div>
  );
};

export default Post;