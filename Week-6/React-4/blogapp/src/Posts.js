import React from 'react';
import Post from './Post'; 

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null,
      isLoading: true 
    };
  }

  render() {
    const { posts, error, isLoading } = this.state;

    if (isLoading) {
      return <div style={{ textAlign: 'center', padding: '20px' }}>Loading posts...</div>;
    }

    if (error) {
      return <div style={{ color: 'red', textAlign: 'center', padding: '20px' }}>Error: {error.message}</div>;
    }

    return (
      <div style={{ maxWidth: '800px', margin: '20px auto', padding: '20px', backgroundColor: '#fff', borderRadius: '10px', boxShadow: '0 4px 15px rgba(0,0,0,0.1)' }}>
        <h2 style={{ textAlign: 'center', color: '#007bff' }}>Latest Blog Posts</h2>
        {posts.map(post => (
          <Post key={post.id} title={post.title} body={post.body} />
        ))}
      </div>
    );
  }

async loadPosts() {
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts');
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    this.setState({
      posts: data,
      isLoading: false
    });
  } catch (error) {
    console.error("Failed to fetch posts:", error);
    this.setState({
      error: error, 
      isLoading: false 
    });
  }
}

componentDidMount() {
  console.log("Posts component did mount. Fetching data...");
  this.loadPosts(); 
}

componentDidCatch(error, info) {
  console.error("Error caught by componentDidCatch:", error, info);
  this.setState({
    error: error, 
    isLoading: false 
  });
  alert(`An error occurred in a child component: ${error.message}\nCheck console for details.`);
}
}

export default Posts;